package tools

import (
	"context"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"strings"

	"github.com/api-v1/mcp-server/config"
	"github.com/api-v1/mcp-server/models"
	"github.com/mark3labs/mcp-go/mcp"
)

func Post_api_v1_donations_createHandler(cfg *config.APIConfig) func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
	return func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
		args, ok := request.Params.Arguments.(map[string]any)
		if !ok {
			return mcp.NewToolResultError("Invalid arguments object"), nil
		}
		queryParams := make([]string, 0)
		if val, ok := args["amount"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("amount=%v", val))
		}
		if val, ok := args["nonprofit_id"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("nonprofit_id=%v", val))
		}
		if val, ok := args["funding_source"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("funding_source=%v", val))
		}
		if val, ok := args["zip_code"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("zip_code=%v", val))
		}
		queryString := ""
		if len(queryParams) > 0 {
			queryString = "?" + strings.Join(queryParams, "&")
		}
		url := fmt.Sprintf("%s/api/v1/donations/create%s", cfg.BaseURL, queryString)
		req, err := http.NewRequest("POST", url, nil)
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Failed to create request", err), nil
		}
		// Set authentication based on auth type
		if cfg.BasicAuth != "" {
			req.Header.Set("Authorization", fmt.Sprintf("Basic %s", cfg.BasicAuth))
		}
		req.Header.Set("Accept", "application/json")

		resp, err := http.DefaultClient.Do(req)
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Request failed", err), nil
		}
		defer resp.Body.Close()

		body, err := io.ReadAll(resp.Body)
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Failed to read response body", err), nil
		}

		if resp.StatusCode >= 400 {
			return mcp.NewToolResultError(fmt.Sprintf("API error: %s", body)), nil
		}
		// Use properly typed response
		var result map[string]interface{}
		if err := json.Unmarshal(body, &result); err != nil {
			// Fallback to raw text if unmarshaling fails
			return mcp.NewToolResultText(string(body)), nil
		}

		prettyJSON, err := json.MarshalIndent(result, "", "  ")
		if err != nil {
			return mcp.NewToolResultErrorFromErr("Failed to format JSON", err), nil
		}

		return mcp.NewToolResultText(string(prettyJSON)), nil
	}
}

func CreatePost_api_v1_donations_createTool(cfg *config.APIConfig) models.Tool {
	tool := mcp.NewTool("post_api_v1_donations_create",
		mcp.WithDescription("Create a donation"),
		mcp.WithString("amount", mcp.Required(), mcp.Description("The amount of the donation in cents.")),
		mcp.WithString("nonprofit_id", mcp.Required(), mcp.Description("The id of a nonprofit from the CHANGE network.")),
		mcp.WithString("funding_source", mcp.Required(), mcp.Description("Source of the donation funds. If you are collecting payment from your customer for the donation, use `customer`.")),
		mcp.WithString("zip_code", mcp.Description("The customer's zip code. Provide this to unlock geographic insights.")),
	)

	return models.Tool{
		Definition: tool,
		Handler:    Post_api_v1_donations_createHandler(cfg),
	}
}

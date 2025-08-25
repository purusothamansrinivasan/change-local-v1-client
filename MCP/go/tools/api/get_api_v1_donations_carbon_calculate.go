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

func Get_api_v1_donations_carbon_calculateHandler(cfg *config.APIConfig) func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
	return func(ctx context.Context, request mcp.CallToolRequest) (*mcp.CallToolResult, error) {
		args, ok := request.Params.Arguments.(map[string]any)
		if !ok {
			return mcp.NewToolResultError("Invalid arguments object"), nil
		}
		queryParams := make([]string, 0)
		if val, ok := args["origin_address"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("origin_address=%v", val))
		}
		if val, ok := args["destination_address"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("destination_address=%v", val))
		}
		if val, ok := args["distance_mi"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("distance_mi=%v", val))
		}
		if val, ok := args["weight_lb"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("weight_lb=%v", val))
		}
		if val, ok := args["transportation_method"]; ok {
			queryParams = append(queryParams, fmt.Sprintf("transportation_method=%v", val))
		}
		queryString := ""
		if len(queryParams) > 0 {
			queryString = "?" + strings.Join(queryParams, "&")
		}
		url := fmt.Sprintf("%s/api/v1/donations/carbon_calculate%s", cfg.BaseURL, queryString)
		req, err := http.NewRequest("GET", url, nil)
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

func CreateGet_api_v1_donations_carbon_calculateTool(cfg *config.APIConfig) models.Tool {
	tool := mcp.NewTool("get_api_v1_donations_carbon_calculate",
		mcp.WithDescription("Calculate shipping carbon offset"),
		mcp.WithString("origin_address", mcp.Description("The origin zip code (US only) of the shipment. If you send this parameter, also send `destination_address`.")),
		mcp.WithString("destination_address", mcp.Description("The destination zip code (US only) of the shipment. If you send this parameter, also send `origin_address`.")),
		mcp.WithString("distance_mi", mcp.Description("The total distance (in miles) of the shipment. You can use this parameter in place of `origin_address` and `destination_address`.")),
		mcp.WithString("weight_lb", mcp.Required(), mcp.Description("The total weight (in pounds) of the shipment.")),
		mcp.WithString("transportation_method", mcp.Description("The primary transportation method of the shipment.")),
	)

	return models.Tool{
		Definition: tool,
		Handler:    Get_api_v1_donations_carbon_calculateHandler(cfg),
	}
}

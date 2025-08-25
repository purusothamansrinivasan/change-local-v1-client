/**
 * MCP Server function for Create a donation
 */

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.function.Function;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

class Post_Api_V1_Donations_CreateMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getPost_Api_V1_Donations_CreateHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("amount")) {
            queryParams.add("amount=" + args.get("amount"));
        }
        if (args.containsKey("nonprofit_id")) {
            queryParams.add("nonprofit_id=" + args.get("nonprofit_id"));
        }
        if (args.containsKey("funding_source")) {
            queryParams.add("funding_source=" + args.get("funding_source"));
        }
        if (args.containsKey("zip_code")) {
            queryParams.add("zip_code=" + args.get("zip_code"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/post_api_v1_donations_create" + queryString;
                
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + config.getApiKey())
                    .header("Accept", "application/json")
                    .GET()
                    .build();
                
                HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                
                if (response.statusCode() >= 400) {
                    return new MCPServer.MCPToolResult("API error: " + response.body(), true);
                }
                
                // Pretty print JSON
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(response.body());
                String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                
                return new MCPServer.MCPToolResult(prettyJson);
                
            } catch (IOException | InterruptedException e) {
                return new MCPServer.MCPToolResult("Request failed: " + e.getMessage(), true);
            } catch (Exception e) {
                return new MCPServer.MCPToolResult("Unexpected error: " + e.getMessage(), true);
            }
        };
    }
    
    public static MCPServer.Tool createPost_Api_V1_Donations_CreateTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> amountProperty = new HashMap<>();
        amountProperty.put("type", "string");
        amountProperty.put("required", true);
        amountProperty.put("description", "The amount of the donation in cents.");
        properties.put("amount", amountProperty);
        Map<String, Object> nonprofit_idProperty = new HashMap<>();
        nonprofit_idProperty.put("type", "string");
        nonprofit_idProperty.put("required", true);
        nonprofit_idProperty.put("description", "The id of a nonprofit from the CHANGE network.");
        properties.put("nonprofit_id", nonprofit_idProperty);
        Map<String, Object> funding_sourceProperty = new HashMap<>();
        funding_sourceProperty.put("type", "string");
        funding_sourceProperty.put("required", true);
        funding_sourceProperty.put("description", "Source of the donation funds. If you are collecting payment from your customer for the donation, use `customer`.");
        properties.put("funding_source", funding_sourceProperty);
        Map<String, Object> zip_codeProperty = new HashMap<>();
        zip_codeProperty.put("type", "string");
        zip_codeProperty.put("required", false);
        zip_codeProperty.put("description", "The customer's zip code. Provide this to unlock geographic insights.");
        properties.put("zip_code", zip_codeProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "post_api_v1_donations_create",
            "Create a donation",
            parameters
        );
        
        return new MCPServer.Tool(definition, getPost_Api_V1_Donations_CreateHandler(config));
    }
    
}
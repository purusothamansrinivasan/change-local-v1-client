/**
 * MCP Server function for Retrieve carbon offset stats
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

class Get_Api_V1_Donations_Carbon_StatsMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Api_V1_Donations_Carbon_StatsHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("id")) {
            queryParams.add("id=" + args.get("id"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_api_v1_donations_carbon_stats" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Api_V1_Donations_Carbon_StatsTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> idProperty = new HashMap<>();
        idProperty.put("type", "string");
        idProperty.put("required", false);
        idProperty.put("description", "The id of a donation to the CarbonFund nonprofit. Ids are returned when a donation is created. If an ID is not provided, the total stats for all donations to CarbonFund are returned.");
        properties.put("id", idProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_api_v1_donations_carbon_stats",
            "Retrieve carbon offset stats",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Api_V1_Donations_Carbon_StatsHandler(config));
    }
    
}
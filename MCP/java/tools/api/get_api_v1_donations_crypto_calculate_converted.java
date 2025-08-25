/**
 * MCP Server function for Calculate crypto carbon offset
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

class Get_Api_V1_Donations_Crypto_CalculateMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Api_V1_Donations_Crypto_CalculateHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("count")) {
            queryParams.add("count=" + args.get("count"));
        }
        if (args.containsKey("currency")) {
            queryParams.add("currency=" + args.get("currency"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_api_v1_donations_crypto_calculate" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Api_V1_Donations_Crypto_CalculateTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> countProperty = new HashMap<>();
        countProperty.put("type", "string");
        countProperty.put("required", false);
        countProperty.put("description", "The number of transactions to offset.");
        properties.put("count", countProperty);
        Map<String, Object> currencyProperty = new HashMap<>();
        currencyProperty.put("type", "string");
        currencyProperty.put("required", true);
        currencyProperty.put("description", "The currency of the transaction.");
        properties.put("currency", currencyProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_api_v1_donations_crypto_calculate",
            "Calculate crypto carbon offset",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Api_V1_Donations_Crypto_CalculateHandler(config));
    }
    
}
/**
 * MCP Server function for Calculate shipping carbon offset
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

class Get_Api_V1_Donations_Carbon_CalculateMCPTool {
    
    public static Function<MCPServer.MCPRequest, MCPServer.MCPToolResult> getGet_Api_V1_Donations_Carbon_CalculateHandler(MCPServer.APIConfig config) {
        return (request) -> {
            try {
                Map<String, Object> args = request.getArguments();
                if (args == null) {
                    return new MCPServer.MCPToolResult("Invalid arguments object", true);
                }
                
                List<String> queryParams = new ArrayList<>();
        if (args.containsKey("origin_address")) {
            queryParams.add("origin_address=" + args.get("origin_address"));
        }
        if (args.containsKey("destination_address")) {
            queryParams.add("destination_address=" + args.get("destination_address"));
        }
        if (args.containsKey("distance_mi")) {
            queryParams.add("distance_mi=" + args.get("distance_mi"));
        }
        if (args.containsKey("weight_lb")) {
            queryParams.add("weight_lb=" + args.get("weight_lb"));
        }
        if (args.containsKey("transportation_method")) {
            queryParams.add("transportation_method=" + args.get("transportation_method"));
        }
                
                String queryString = queryParams.isEmpty() ? "" : "?" + String.join("&", queryParams);
                String url = config.getBaseUrl() + "/api/v2/get_api_v1_donations_carbon_calculate" + queryString;
                
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
    
    public static MCPServer.Tool createGet_Api_V1_Donations_Carbon_CalculateTool(MCPServer.APIConfig config) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("type", "object");
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> origin_addressProperty = new HashMap<>();
        origin_addressProperty.put("type", "string");
        origin_addressProperty.put("required", false);
        origin_addressProperty.put("description", "The origin zip code (US only) of the shipment. If you send this parameter, also send `destination_address`.");
        properties.put("origin_address", origin_addressProperty);
        Map<String, Object> destination_addressProperty = new HashMap<>();
        destination_addressProperty.put("type", "string");
        destination_addressProperty.put("required", false);
        destination_addressProperty.put("description", "The destination zip code (US only) of the shipment. If you send this parameter, also send `origin_address`.");
        properties.put("destination_address", destination_addressProperty);
        Map<String, Object> distance_miProperty = new HashMap<>();
        distance_miProperty.put("type", "string");
        distance_miProperty.put("required", false);
        distance_miProperty.put("description", "The total distance (in miles) of the shipment. You can use this parameter in place of `origin_address` and `destination_address`.");
        properties.put("distance_mi", distance_miProperty);
        Map<String, Object> weight_lbProperty = new HashMap<>();
        weight_lbProperty.put("type", "string");
        weight_lbProperty.put("required", true);
        weight_lbProperty.put("description", "The total weight (in pounds) of the shipment.");
        properties.put("weight_lb", weight_lbProperty);
        Map<String, Object> transportation_methodProperty = new HashMap<>();
        transportation_methodProperty.put("type", "string");
        transportation_methodProperty.put("required", false);
        transportation_methodProperty.put("description", "The primary transportation method of the shipment.");
        properties.put("transportation_method", transportation_methodProperty);
        parameters.put("properties", properties);
        
        MCPServer.ToolDefinition definition = new MCPServer.ToolDefinition(
            "get_api_v1_donations_carbon_calculate",
            "Calculate shipping carbon offset",
            parameters
        );
        
        return new MCPServer.Tool(definition, getGet_Api_V1_Donations_Carbon_CalculateHandler(config));
    }
    
}
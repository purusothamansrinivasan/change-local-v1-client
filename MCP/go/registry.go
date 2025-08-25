package main

import (
	"github.com/api-v1/mcp-server/config"
	"github.com/api-v1/mcp-server/models"
	tools_api "github.com/api-v1/mcp-server/tools/api"
)

func GetAll(cfg *config.APIConfig) []models.Tool {
	return []models.Tool{
		tools_api.CreateGet_api_v1_donations_indexTool(cfg),
		tools_api.CreateGet_api_v1_donations_showTool(cfg),
		tools_api.CreateGet_api_v1_nonprofits_listTool(cfg),
		tools_api.CreateGet_api_v1_nonprofits_showTool(cfg),
		tools_api.CreateGet_api_v1_donations_carbon_calculateTool(cfg),
		tools_api.CreateGet_api_v1_donations_carbon_statsTool(cfg),
		tools_api.CreatePost_api_v1_donations_createTool(cfg),
		tools_api.CreateGet_api_v1_donations_crypto_calculateTool(cfg),
	}
}

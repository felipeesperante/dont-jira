# Swagger Client

Os serviços consumidos pela SPA devem ser gerados a partir do contrato Swagger/OpenAPI.

Passos sugeridos:
1. Exportar contrato `openapi.json`.
2. Gerar client TypeScript (ex.: openapi-generator).
3. Publicar arquivos gerados nesta pasta.
4. Consumir na aplicação exclusivamente via adaptadores de `frontend/src/services`.

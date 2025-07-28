import { CodegenConfig } from "@graphql-codegen/cli";

const config: CodegenConfig = {
	schema: "../backend/src/main/resources/schema/schema.graphqls",
	documents: ["src/**/*.tsx"],
	generates: {


		"./src/__generated__/": {
			preset: "client"
		},

	}
};

export default config;

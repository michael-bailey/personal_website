import type { NextConfig } from "next";
import {getApiBase} from "@/lib/environment";

const nextConfig: NextConfig = {
  /* config options here */
	output: "standalone",
	rewrites: async () => [
		{
			source: '/api/:path*',
			destination: `${getApiBase()}/api/:path*`,
		},
		{
			source: '/graphql',
			destination: `${getApiBase()}/graphql/`,
		},
	],
	redirects: async () => [
		{
			source: "/graphiql",
			destination: `${getApiBase()}/graphiql`,
			permanent: true,
		}
	]
};

export default nextConfig;

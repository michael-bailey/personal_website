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
	],
	images: {
		domains: ["avatars.githubusercontent.com","localhost", "michael-bailey.net", "new.michael-bailey.net"],
	}
};

export default nextConfig;

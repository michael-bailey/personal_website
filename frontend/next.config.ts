import type {NextConfig} from "next";

const nextConfig: NextConfig = {
	/* config options here */
	output: "standalone",
	images: {
		domains: ["spring-blog-app", "avatars.githubusercontent.com", "localhost", "michael-bailey.net", "new.michael-bailey.net"],
	}
};

export default nextConfig;

export const getApiBase = (isSSR: boolean) => {
	return isSSR ? (process.env.API_HOSTNAME ?? "Set this up better") : ""
}
export const getApiBase = () => {
	return process.env.API_HOSTNAME || process.env.NEXT_PUBLIC_API_HOSTNAME
}
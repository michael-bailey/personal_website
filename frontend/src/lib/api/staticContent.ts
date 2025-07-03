import {getApiBase} from "@/lib/environment";

export const getImageUrl = (image: string) => getApiBase() + `/img/${image}`;
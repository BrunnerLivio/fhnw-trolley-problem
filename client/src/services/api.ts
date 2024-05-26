import ky from "ky";
import { errorHandlerService } from "./ErrorHandlerService";

export const api = ky.create({
    prefixUrl: import.meta.env.VITE_API_URL,
    hooks: {
        beforeError: [
            async (error) => {
                const { response } = error;
                const body = await response.json();
                errorHandlerService.errorHandler(body, response.status);
                return error;
            },
        ],
    },
});

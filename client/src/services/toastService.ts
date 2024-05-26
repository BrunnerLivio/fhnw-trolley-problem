import { toast } from "@zerodevx/svelte-toast";

const error = (message: string) => {
    const theme = {
        "--toastBackground": "red",
        "--toastColor": "white",
        "--toastBarBackground": "white",
    };

    toast.push(message, {
        theme,
    });
};

const success = (message: string) => {
    const theme = {
        "--toastBackground": "green",
        "--toastColor": "white",
        "--toastBarBackground": "white",
    };

    toast.push(message, {
        theme,
    });
};

export const toastService = {
    error,
    success,
};

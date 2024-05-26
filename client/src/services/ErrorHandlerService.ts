import { toastService } from "./toastService";

type ValidationError = {
    field: string;
    bindingFailure: boolean;
    code: string;
    codes: string[];
    defaultMessage: string;
    objectName: string;
    rejectedValue: string;
};

type ValidationErrorResponse = {
    error: string;
    errors: ValidationError[];
    message: string;
    path: string;
};

const isValidationErrorResponse = (
    error: any
): error is ValidationErrorResponse => {
    return "errors" in error;
};

const errorHandler = async (error: any) => {
    if (isValidationErrorResponse(error)) {
        return toastService.error(error.errors[0].defaultMessage);
    }
    return toastService.error("An error occurred");
};

export const errorHandlerService = {
    errorHandler,
};

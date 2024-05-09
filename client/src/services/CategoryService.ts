import type { Category } from "../models/Category";
import type { Problem } from "../models/Problem";
import { api } from "./api";

const list = async () => {
    const response = await api.get("api/categories/");
    return await response.json<Category[]>();
};

export const randomProblem = async (categoryName: string) => {
    const response = await api.get(
        `api/categories/${categoryName}/problems/random`
    );
    return await response.json<Problem>();
};

export const categoryService = {
    list,
    randomProblem,
};

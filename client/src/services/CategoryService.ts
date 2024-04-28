import type { Category } from "../models/Category";
import { api } from "./api";

const list = async () => {
    const response = await api.get("api/categories/");
    return await response.json<Category[]>();
};

export const categoryService = {
    list,
};

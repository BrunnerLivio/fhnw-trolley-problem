import type { Category } from "../models/Category";
import type { Scenario } from "../models/Scenario";
import type { RandomScenario } from "../models/RandomScenario";
import { api } from "./api";

const list = async () => {
    const response = await api.get("api/categories/");
    return await response.json<Category[]>();
};

export const randomScenario = async (categoryName: string) => {
    const response = await api.get(
        `api/categories/${categoryName}/scenarios/random`,
    );
    return await response.json<RandomScenario>();
};

export const scenarioInCategory = async (categoryName: string, id: number) => {
    const response = await api.get(
        `api/categories/${categoryName}/scenarios/${id}`,
    );
    return await response.json<Scenario>();
};

export const categoryService = {
    list,
    randomScenario,
    scenarioInCategory,
};

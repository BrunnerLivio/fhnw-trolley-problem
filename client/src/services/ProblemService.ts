import type { Position } from "../models/Position";
import type { Problem } from "../models/Problem";
import type { ProblemCreate } from "../models/ProblemCreate";
import { api } from "./api";

const list = async () => {
    const response = await api.get("api/problems");
    return await response.json<Problem[]>();
};

const random = async () => {
    const response = await api.get("api/problems/random");
    return await response.json<Problem>();
};

const create = async (problem: ProblemCreate) => {
    const response = await api.post("api/problems/", {
        json: problem,
    });
    return await response.json<Problem>();
};

const vote = async (problemId: number, position: Position) => {
    const response = await api.post(
        `api/problems/${problemId}/vote/${position}`
    );
    return await response.json<Problem>();
};

const detail = async (problemId: number) => {
    const response = await api.get(`api/problems/${problemId}`);
    return await response.json<Problem>();
};

export const problemService = {
    list,
    random,
    vote,
    detail,
    create,
};

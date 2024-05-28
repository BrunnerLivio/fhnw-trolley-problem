import type { Comment } from "../models/Comment";
import type { CommentCreate } from "../models/CommentCreate";
import type { Position } from "../models/Position";
import type { Scenario } from "../models/Scenario";
import type { ScenarioCreate } from "../models/ScenarioCreate";
import { api } from "./api";

const list = async () => {
    const response = await api.get("api/scenarios");
    return await response.json<Scenario[]>();
};

const random = async () => {
    const response = await api.get("api/scenarios/random");
    return await response.json<Scenario>();
};

const create = async (scenario: ScenarioCreate) => {
    const response = await api.post("api/scenarios/", {
        json: scenario,
    });
    return await response.json<Scenario>();
};

const vote = async (scenarioId: number, position: Position) => {
    const response = await api.post(
        `api/scenarios/${scenarioId}/vote/${position}`,
    );
    return await response.json<Scenario>();
};

const comments = async (scenarioId: number) => {
    const response = await api.get(`api/scenarios/${scenarioId}/comments`);
    return await response.json<Comment[]>();
};

const createComment = async (scenarioId: number, comment: CommentCreate) => {
    const response = await api.post(`api/scenarios/${scenarioId}/comments`, {
        json: comment,
    });
    return await response.json<Comment>();
};

const detail = async (scenarioId: number) => {
    const response = await api.get(`api/scenarios/${scenarioId}`);
    return await response.json<Scenario>();
};

export const scenarioService = {
    list,
    random,
    vote,
    detail,
    comments,
    createComment,
    create,
};

import type { CommentCreate } from "../models/CommentCreate";
import { api } from "./api";

const update = async (id: number, comment: CommentCreate): Promise<Comment> => {
    const response = await api.put(`api/comments/${id}`, {
        json: comment,
    });
    return await response.json<Comment>();
};

const remove = async (id: number): Promise<void> => {
    await api.delete(`api/comments/${id}`);
};

export const commentService = {
    update,
    remove,
};

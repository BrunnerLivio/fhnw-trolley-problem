import type { Victim } from "../models/Victim";
import { api } from "./api";

const list = async () => {
    const response = await api.get("api/victims/");
    return await response.json<Victim[]>();
};

export const victimService = {
    list,
};

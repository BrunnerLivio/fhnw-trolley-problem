import ky from "ky";

type Problem = {
    id: number;
    question: string;
    // createdAt: string;
    leftVotes: number;
    rightVotes: number;
    category: {
        id: number;
        name: string;
    };
    leftVictims: Victim[];
    rightVictims: Victim[];
};

type Victim = {
    id: number;
    name: string;
    imageUrl: string;
}

const api = ky.create({
    prefixUrl: import.meta.env.VITE_API_URL,
});

const list = async () => {
    const response = await api.get("api/problems");
    return await response.json<Problem[]>();
};

const random = async () => {
    const response = await api.get("api/problems/random");
    return await response.json<Problem>();
};

export const problemService = {
    list,
    random,
};

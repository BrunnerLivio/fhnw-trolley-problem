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
};

export const TrackPosition = {
    LEFT: 0,
    RIGHT: 1,
} as const;
type TrackPosition = (typeof TrackPosition)[keyof typeof TrackPosition];

export const Position = {
    LEFT: "LEFT",
    RIGHT: "RIGHT",
} as const;
export type Positon = (typeof Position)[keyof typeof Position];

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

const vote = async (problemId: number, position: Positon) => {
    const response = await api.post(
        `api/problems/${problemId}/vote/${position}`
    );
    return await response.json<Problem>();
};

export const problemService = {
    list,
    random,
    vote,
};

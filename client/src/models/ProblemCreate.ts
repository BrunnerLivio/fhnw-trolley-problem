import type { Victim } from "./Victim";

export type ProblemCreate = {
    question: string;
    categoryId: number;
    leftVictims: Victim[];
    rightVictims: Victim[];
    leftLabel: string | null;
    rightLabel: string | null;
};

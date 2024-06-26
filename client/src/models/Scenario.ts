import type { Category } from "./Category";
import type { Victim } from "./Victim";

export type Scenario = {
    id: number;
    question: string;
    // createdAt: string;
    leftVotes: number;
    rightVotes: number;
    category: Category;
    leftVictims: Victim[];
    rightVictims: Victim[];
    leftLabel: string | null;
    rightLabel: string | null;
    nextScenarioId: number | null;
};

import type { Victim } from "./Victim";

export type ScenarioCreate = {
    question: string;
    categoryId: number;
    leftVictims: Victim[];
    rightVictims: Victim[];
    leftLabel: string | null;
    rightLabel: string | null;
};

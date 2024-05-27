import { writable } from "svelte/store";

const stored = localStorage.getItem("problems");

export type ProblemStore = {
    viewed: number[];
};

export const problemStore = writable<ProblemStore>(
    JSON.parse(stored ?? '{ "viewed": []}'),
);
problemStore.subscribe((value) =>
    localStorage.setItem("problems", JSON.stringify(value)),
);

export const Directional = {
    LEFT: "LEFT",
    RIGHT: "RIGHT",
} as const;
export type Directional = (typeof Directional)[keyof typeof Directional];

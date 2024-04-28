export const Position = {
    LEFT: "LEFT",
    RIGHT: "RIGHT",
} as const;
export type Position = (typeof Position)[keyof typeof Position];

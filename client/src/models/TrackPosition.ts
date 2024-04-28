export const TrackPosition = {
    LEFT: 0,
    RIGHT: 1,
} as const;
type TrackPosition = (typeof TrackPosition)[keyof typeof TrackPosition];

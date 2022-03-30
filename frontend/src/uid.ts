let nextId = 0;

/**
 * Generates a unique sequential id.
 * @returns A number that is the unique id.
 */
export function uid(): number {
    return nextId++;
}

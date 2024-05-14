<script lang="ts">
    import { createEventDispatcher } from "svelte";
    import type { Victim } from "../../models/Victim";

    export let victim: Victim;
    export let index: number;
    export let yOffsetMultiplier: number;
    export let showDelete: boolean;

    const dispatch = createEventDispatcher<{ delete: null }>();

    $: ratio = 0;

    const onLoad = (e: Event) => {
        const img = e.target as HTMLImageElement;
        ratio = img.naturalWidth / img.naturalHeight;
    };
</script>

<div
    class="absolute w-full"
    style:left={index * 10 + "%"}
    style:top={index * yOffsetMultiplier + "%"}
    style:max-width={ratio * 40 + "%"}
>
    {#if showDelete}
        <button
            class="absolute z-20 p-1 text-white bg-red-500 rounded-full -top-4 -right-4"
            on:click={() => dispatch("delete", null)}
        >
            <svg
                class="w-4 h-4"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg"
            >
                <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    stroke-width="2"
                    d="M6 18L18 6M6 6l12 12"
                />
            </svg>
        </button>
    {/if}
    <img
        on:load={(e) => onLoad(e)}
        class="w-full"
        src={victim.imageUrl}
        alt={victim.name}
    />
</div>

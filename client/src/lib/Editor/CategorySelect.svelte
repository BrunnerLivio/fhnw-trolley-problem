<script lang="ts">
    import { createEventDispatcher } from "svelte";
    import type { Category } from "../../models/Category";

    export let categories: Category[];
    export let value: number;
    $: selectedValue = value || categories[0].id;

    const dispatch = createEventDispatcher<{ change: number }>();

    const handleChange = (e: Event) => {
        const select = e.target as HTMLSelectElement;
        dispatch("change", parseInt(select.value));
    };
</script>

<div class="flex gap-4">
    <span class="text-2xl">Category:</span>

    <select
        value={selectedValue}
        on:change={e => handleChange(e)}
        class="flex-1 text-2xl bg-transparent border-b-2 border-b-primary"
    >
        {#each categories as category}
            <option value={category.id}>{category.name}</option>
        {/each}
    </select>
</div>

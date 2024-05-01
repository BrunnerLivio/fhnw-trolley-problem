<script lang="ts">
    import type { Category } from "../../models/Category";

    export let value: number;
    export let categories: Category[];
</script>

<div class="flex gap-4">
    <span class="text-2xl">Category:</span>

    <select
        bind:value={value}
        class="flex-1 text-2xl bg-transparent border-b-2 border-b-primary"
    >
        {#each categories as category}
            <option value={category.id}>{category.name}</option>
        {/each}
    </select>
</div>

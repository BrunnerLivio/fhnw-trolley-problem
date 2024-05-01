<script lang="ts">
    export let leftLabel: string | null;
    export let rightLabel: string | null;

</script>

<div class="flex flex-col gap-4">
    <span>Left Label:</span>

    <input
        type="text"
        bind:value={leftLabel}
        class="flex-1 bg-transparent border-b-2 border-b-primary"
    />
</div>

<div class="flex flex-col gap-4">
    <span>Right Label:</span>

    <input
        type="text"
        bind:value={rightLabel}
        class="flex-1 bg-transparent border-b-2 border-b-primary"
    />
</div>

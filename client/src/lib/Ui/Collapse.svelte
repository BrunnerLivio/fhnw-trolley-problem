<script lang="ts">
    let open = false;

    export let title = "";
</script>

<div class="flex flex-col w-full gap-4">
    <button
        class="flex items-center justify-between w-full py-4 text-xl border-b-2 cursor-pointer border-primary"
        style:border-bottom-width={open ? "0" : "2px"}
        on:click={() => (open = !open)}
    >
        <span>{title}</span>
        <span>{open ? "-" : "+"}</span>
    </button>
    {#if open}
        <slot />
    {/if}
</div>

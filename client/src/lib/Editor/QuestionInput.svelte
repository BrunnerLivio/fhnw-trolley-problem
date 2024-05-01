<script lang="ts">
    import type { Problem } from "../../models/Problem";

    export let problem: Partial<Problem>;
    $: placeholder = `A trolley is heading towards ${problem?.rightVictims?.length} people. You can pull the lever to divert it to the other track, killing ${problem?.leftVictims?.length} person instead. What do you do?`;

    const adjustDynamicTextArea = (e: Event) => {
        const element = e.target as HTMLTextAreaElement;
        element.style.height = "5px";
        element.style.height = `${element.scrollHeight}px`;
    };
</script>

<div class="flex w-full gap-2">
    <span class="text-2xl">Oh no!</span>
    <textarea
        bind:value={problem.question}
        on:input={(e) => adjustDynamicTextArea(e)}
        {placeholder}
        class="flex-1 text-2xl bg-transparent border-b-2 resize-none min-h-20 border-b-primary"
    />
</div>

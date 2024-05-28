<script lang="ts">
    import type { Scenario } from "../../models/Scenario";

    export let scenario: Partial<Scenario>;
    $: placeholder = `A trolley is heading towards ${scenario?.rightVictims?.length} people. You can pull the lever to divert it to the other track, killing ${scenario?.leftVictims?.length} person instead. What do you do?`;

    const adjustDynamicTextArea = (e: Event) => {
        const element = e.target as HTMLTextAreaElement;
        element.style.height = "5px";
        element.style.height = `${element.scrollHeight}px`;
    };
</script>

<div class="flex w-full gap-2">
    <span class="text-2xl">Oh no!</span>
    <textarea
        bind:value={scenario.question}
        on:input={(e) => adjustDynamicTextArea(e)}
        required
        minlength="10"
        maxlength="255"
        {placeholder}
        class="flex-1 text-2xl bg-transparent border-b-2 resize-none min-h-20 border-b-primary"
    />
</div>

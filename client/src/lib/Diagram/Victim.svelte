<script lang="ts">
    import type { Victim } from "../../models/Victim";

    export let victim: Victim;
    export let index: number;
    export let yOffsetMultiplier: number;

    $: ratio = 0;

    const onLoad = (e: Event) => {
        const img = e.target as HTMLImageElement;
        ratio = img.naturalWidth / img.naturalHeight;
    };

</script>

<img
    on:load={(e) => onLoad(e)}
    class="w-full max-w-[35%]"
    style:transform={`translateY(${index * yOffsetMultiplier}%) translateX(${index * -65}%)`}
    src={victim.imageUrl}
    alt={victim.name}
/>

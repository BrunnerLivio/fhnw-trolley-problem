<script lang="ts">
    import type { HTMLButtonAttributes } from "svelte/elements";
    import { cva, type VariantProps } from "class-variance-authority";

    const button = cva("p-4 text-xl border-2 rounded-lg border-primary", {
        variants: {
            intent: {
                primary:
                    "bg-primary text-background hover:bg-background hover:text-primary",
                secondary: "hover:bg-primary hover:text-background",
            },
        },
        compoundVariants: [{ intent: "primary" }],
    });

    interface $$Props
        extends HTMLButtonAttributes,
            VariantProps<typeof button> {}

    /**
     * For Svelte components, we recommend setting your defaultVariants within
     * Svelte props (which are `undefined` by default)
     */
    export let intent: $$Props["intent"] = "primary";
</script>

<button
    {...$$restProps}
    on:click
    on:mouseover
    on:mouseenter
    on:mouseleave
    on:focus
    class={button({ intent })}
>
    <slot></slot>
</button>

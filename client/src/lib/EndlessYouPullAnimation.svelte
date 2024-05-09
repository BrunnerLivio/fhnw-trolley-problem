<script lang="ts">
    import You from "../assets/you.svg";
    import YouPull from "../assets/you-pull.svg";
    import type { HTMLImgAttributes } from "svelte/elements";

    $: pull = false;

    setInterval(() => {
        pull = !pull;
    }, 200);

    interface $$Props extends HTMLImgAttributes {}
</script>

<img src={pull ? YouPull : You} {...$$props} alt="You" />

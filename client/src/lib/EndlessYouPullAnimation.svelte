<script lang="ts">
    import You from "../assets/you.svg";
    import YouPull from "../assets/you-pull.svg";

    $: pull = false;

    setInterval(() => {
        pull = !pull;
    }, 200);
</script>

<img src={pull ? YouPull : You} alt="You" class="w-1/2" />

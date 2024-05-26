<script>
    import { link } from "svelte-spa-router";
</script>

<nav
    class="flex items-center justify-center w-full py-4 border-b-2 shadow-md border-primary"
>
    <a href="/" use:link>
        <h1
            class="flex text-xl text-center transition-transform md:text-2xl hover:scale-110"
        >
            The Trolley Problem
        </h1>
    </a>
    <a
        href="/about"
        class="absolute flex justify-center transition-transform iems-center right-10 hover:scale-110"
        use:link
    >
        About
    </a>
</nav>

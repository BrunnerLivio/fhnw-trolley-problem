<script lang="ts">
    export let percentage: number;
    $: strokeDasharray = `${(percentage / 100) * 31.5} 31.4`;
</script>

<svg
    width="100%"
    height="100%"
    viewBox="0 0 20 20"
    class="w-10 h-10"
    style="fill-rule: evenodd; clip-rule: evenodd;"
>
    <path
        id="outer"
        d="M10,1C14.873,1.576 19.207,5.677 19,10C18.766,14.888 14.847,18.965 10,19C5.237,19.034 0.755,14.949 1,10C1.222,5.532 5.432,0.46 10,1Z"
        class="fill-white"
    ></path>
    <clipPath id="_clip1">
        <path
            id="outer1"
            d="M10,1C14.873,1.576 19.207,5.677 19,10C18.766,14.888 14.847,18.965 10,19C5.237,19.034 0.755,14.949 1,10C1.222,5.532 5.432,0.46 10,1Z"
        ></path>
    </clipPath>
    <g clip-path="url(#_clip1)">
        <g transform="matrix(-3.82857e-16,-1,1,-3.82857e-16,7.65714e-15,20)">
            <circle
                cx="10"
                cy="10"
                r="5"
                fill="none"
                stroke="black"
                stroke-width="1"
                stroke-dasharray={strokeDasharray}
                class="stroke-[9px]"
            ></circle>
        </g>
        <path
            class="stroke-1 fill-white stroke-primary"
            d="M10,6C12.408,6.204 14.277,8.015 14,10C13.715,12.039 12.075,13.602 10,14C7.832,14.416 6,12.208 6,10C6,7.792 7.8,5.814 10,6Z"
        ></path>
    </g>
    <path
        class="stroke-1 fill-none stroke-primary"
        d="M10,1C14.873,1.576 19.207,5.677 19,10C18.766,14.888 14.847,18.965 10,19C5.237,19.034 0.755,14.949 1,10C1.222,5.532 5.432,0.46 10,1Z"
    ></path>
</svg>

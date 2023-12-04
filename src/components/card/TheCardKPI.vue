<template>
  <div :class="divClass">
    <div :class="`card text-bg-${color}`">
      <div class="card-body text-center">
        <h1>{{ value }}</h1>
        <b>{{ title }}</b>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TheKPICard',

  props: {
    divClass: String,
    color: String,
    value: Number,
    title: String,
  }
}
</script>
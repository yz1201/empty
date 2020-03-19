<template>
  <v-card>
    <v-flex xs12 sm10>
      <v-tree url="/item/category/list"
              :isEdit="isEdit"
              @handleAdd="handleAdd"
              @handleEdit="handleEdit"
              @handleDelete="handleDelete"
              @handleClick="handleClick"
      />
    </v-flex>
  </v-card>
</template>

<script>
  export default {
    name: "category",
    data() {
      return {
        isEdit: true
      }
    },
    methods: {
      handleAdd(node) {
        console.log("add .... ");
        console.log(node);
        this.$http.get('item/category/add', {
          params: {
            // id: node.data.id,
            name: node.name,
            parentId: node.parentId,
            sort: node.sort,
            isParent: node.isParent
          }
        }).then(resp => {
          console.log(resp.data.id);
          console.log("this.id->" + node.id);
          this.loading=true
        })
      },
      handleEdit(id, name) {
        this.$http.get('/item/category/edit', {
          params: {
            id: id,
            name: name
          }
        })
        //   .then(() => {
        //   this.loading = false
        // })

      },
      handleDelete(id) {
        console.log("delete ... " + id);
        this.$http.get('/item/category/delete', {
          params: {
            id: id
          }
        }).then(resp => console.log(resp))
      },
      handleClick(node) {
        console.log(node)
      },
    }
  };
</script>

<style scoped>

</style>

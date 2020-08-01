package tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 11:24
 * @Description:
 */
public class TreeDemo {

    public static List<Entity> treeList = new ArrayList<>();

    static {
        // init list
        treeList.add(new Entity(1L, -1L, "春"));
        treeList.add(new Entity(2L, -1L, "春"));
        treeList.add(new Entity(3L, -1L, "春"));
        treeList.add(new Entity(4L, -1L, "春"));

        treeList.add(new Entity(10L, 1L, "夏1"));
        treeList.add(new Entity(11L, 1L, "夏1"));
        treeList.add(new Entity(12L, 1L, "夏1"));
        treeList.add(new Entity(101L, 10L, "秋1"));
        treeList.add(new Entity(102L, 10L, "秋1"));
        treeList.add(new Entity(103L, 10L, "秋1"));
        treeList.add(new Entity(1010L, 101L, "冬1"));
        treeList.add(new Entity(1011L, 101L, "冬1"));
        treeList.add(new Entity(1012L, 101L, "冬1"));
        treeList.add(new Entity(1013L, 101L, "冬1"));

        treeList.add(new Entity(20L, 2L, "夏2"));
        treeList.add(new Entity(21L, 2L, "夏2"));
        treeList.add(new Entity(22L, 2L, "夏2"));
        treeList.add(new Entity(201L, 20L, "秋1"));
        treeList.add(new Entity(202L, 20L, "秋1"));
        treeList.add(new Entity(203L, 20L, "秋1"));
    }


    public static void main(String[] args) {
        List<Entity> tree = tree(treeList, -1L);
        System.out.println(JSON.toJSONString(tree));
    }

    public static List<Entity> tree(List<Entity> list, Long parentId) {

        List<Entity> childList = getChild(list, parentId);
        for (Entity entity : childList) {
            entity.setChild(getChild(list, entity.getId()));
        }
        return childList;
    }

    public static List<Entity> getChild(List<Entity> list, Long id) {
        List<Entity> child = new ArrayList<>();
        for (Entity entity : list) {
            if (entity.getParentId().equals(id)) {
                child.add(entity);
            }
        }
        return child;
    }
}

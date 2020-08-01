package tree;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author: tn
 * @Date: 2020/8/1 0001 11:25
 * @Description:
 */
@Data
public class Entity {
    public Entity(Long id, Long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    private Long id;
    private Long parentId;
    private String name;
    private List<Entity> child;

}

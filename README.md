![Jsp + Servlet](https://javagitcoder.github.io/assets/gitbook/images/japangoods-spring-index.png "Jsp + Servlet")
```python
# mysql 5.1
import pymysql
from pymysql import Error

def create_tables_and_insert_data():
    # 数据库连接配置 - 请修改为你的实际配置
    db_config = {
        'host': 'hdm336229399.my3w.com',           # 远程MySQL服务器地址
        'port': 3306,                  # MySQL端口，默认3306
        'user': 'hdm336229399',       # 数据库用户名
        'password': '******',   # 数据库密码
        'database': 'hdm336229399_db', # 指定已存在的数据库
        'charset': 'utf8'          # 字符编码
    }
    
    connection = None
    cursor = None
    
    try:
        # 1. 连接到MySQL服务器的japanese_snacks数据库
        connection = pymysql.connect(**db_config)
        cursor = connection.cursor()
        
        print("成功连接到japanese_snacks数据库")
        
        # 2. 创建表（如果不存在）
        create_table_sql = """
        CREATE TABLE IF NOT EXISTS japanese_snacks (
            id INT AUTO_INCREMENT PRIMARY KEY,
            title VARCHAR(100) NOT NULL,
            japanese VARCHAR(100) NOT NULL,
            english VARCHAR(100) NOT NULL,
            description TEXT,
            image_name VARCHAR(255) NOT NULL,
            created_at DATETIME,
            updated_at DATETIME
        )
        """
        cursor.execute(create_table_sql)
        print("数据表创建成功或已存在")
        
        # 3. 清空现有数据（可选，如果需要重新插入）
        # cursor.execute("TRUNCATE TABLE japanese_snacks")
        # print("已清空现有数据")
        
        # 4. 插入示例数据
        insert_sql = """
        INSERT INTO japanese_snacks (title, japanese, english, description, image_name) VALUES
        (%s, %s, %s, %s, %s)
        """
        
        snacks_data = [
            ('巧克力', 'チョコレート', 'Chocolate', '美味的日本巧克力，口感细腻', 'chocolate1001.jpg'),
            ('糖果', 'キャンディ', 'Candy', '各种口味的水果糖', 'sugar1001.jpg'),
            ('薯片', 'ポテトチップス', 'Potato Chips', '薄脆的薯片，多种口味', 'potato-chips1001.jpg'),
            ('杯面', 'カップ麺', 'Cup Noodles', '方便快捷的杯面', 'cup-noodles1001.jpg'),
            ('泡饭', 'お茶漬け', 'Rice in soup', '蒙古泡饭', 'rice-soup1001.jpg'),
            ('泡澡球', 'バスボム', 'Bath Bomb', '可爱的泡澡球', 'bath-bomb1001.jpg'),
            ('饼干', 'ビスケット', 'Cookie', '抹茶饼干', 'cookie1001.jpg'),
            ('饮料', '飲み物', 'Drink', '立顿奶茶', 'drink1001.jpg'),
            ('薯条', 'フライドポテト', 'French fries', '动漫薯条', 'french-fries1001.jpg'),
            ('布丁', 'プリン', 'Pudding', '滑嫩的日式布丁', 'pudding1001.jpg'),
            ('酸奶', 'ヨーグルト', 'Yogurt ', '高千惠酸奶', 'yogurt1001.jpg')
        ]
        
        cursor.executemany(insert_sql, snacks_data)
        connection.commit()
        print(f"成功插入 {len(snacks_data)} 条数据")
        
        # 5. 验证数据插入
        cursor.execute("SELECT COUNT(*) FROM japanese_snacks")
        count = cursor.fetchone()[0]
        print(f"当前零食表中共有 {count} 条记录")
        
        # 6. 显示插入的数据（可选）
        cursor.execute("SELECT id, title, japanese, english FROM japanese_snacks")
        results = cursor.fetchall()
        print("\n插入的数据预览：")
        for row in results:
            print(f"ID: {row[0]}, 标题: {row[1]}, 日文: {row[2]}, 英文: {row[3]}")
        
    except Error as e:
        print(f"数据库操作错误: {e}")
        if connection:
            connection.rollback()
            
    finally:
        # 关闭连接
        if cursor:
            cursor.close()
        if connection:
            connection.close()
            print("数据库连接已关闭")

if __name__ == "__main__":
    create_tables_and_insert_data()
```

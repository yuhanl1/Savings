package tcp;

import javax.swing.plaf.synth.Region;

public class DataJson {
    protected String HeaderKey;

    public DataJson()
    {
    }

    /// <summary>
    /// 转换Socket接收到的信息为对象信息
    /// </summary>
    /// <param name="data">Socket接收到的信息</param>
    public DataJson(String data)
    {
        //Json格式转换后的内容，肯定是小于或者等于实体类的内容
        //因为对象要兼容历史的Json内容,通过反射以最小的成员来赋值
        BaseEntity obj = JsonTools.JsonToObject(data, this.GetType()) as BaseEntity;
        if (obj != null)
        {
            FieldInfo[] fieldArray = ReflectionUtil.GetFields(obj);
            foreach (FieldInfo info in fieldArray)
            {
                object value = ReflectionUtil.GetField(obj, info.Name);
                ReflectionUtil.SetField(this, info.Name, value);
            }                
        }
    }

    /// <summary>
    /// 转换对象为Socket发送格式的字符串
    /// </summary>
    /// <returns></returns>
    public override string ToString()
    {
        String data = "";
        data = JsonTools.ObjectToJson(this);

        if (string.IsNullOrEmpty(HeaderKey))
        {
            throw new ArgumentNullException("DataTypeKey", "实体类未指定协议类型");
        }
        data = NetStringUtil.PackSend(HeaderKey, data);
        return data;
    }
}
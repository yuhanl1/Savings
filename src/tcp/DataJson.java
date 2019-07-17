package tcp;

import javax.swing.plaf.synth.Region;

public class DataJson {
    protected String HeaderKey;

    public DataJson()
    {
    }

    /// <summary>
    /// ת��Socket���յ�����ϢΪ������Ϣ
    /// </summary>
    /// <param name="data">Socket���յ�����Ϣ</param>
    public DataJson(String data)
    {
        //Json��ʽת��������ݣ��϶���С�ڻ��ߵ���ʵ���������
        //��Ϊ����Ҫ������ʷ��Json����,ͨ����������С�ĳ�Ա����ֵ
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
    /// ת������ΪSocket���͸�ʽ���ַ���
    /// </summary>
    /// <returns></returns>
    public override string ToString()
    {
        String data = "";
        data = JsonTools.ObjectToJson(this);

        if (string.IsNullOrEmpty(HeaderKey))
        {
            throw new ArgumentNullException("DataTypeKey", "ʵ����δָ��Э������");
        }
        data = NetStringUtil.PackSend(HeaderKey, data);
        return data;
    }
}
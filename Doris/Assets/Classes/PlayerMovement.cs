using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class PlayerMovement : MonoBehaviour
{
    public Rigidbody2D rb;
    private Animator anim;
    public float speed;

    // Start is called before the first frame update
    void Start()
    {
        anim = GetComponentInChildren<Animator>();
    }

    // Update is called once per frame
    void Update()
    {
        anim.SetFloat("Horizontal", Input.GetAxis("Horizontal"));
        anim.SetFloat("Vertical", Input.GetAxis("Vertical"));
        anim.SetFloat("Magnitude", Mathf.Abs(Input.GetAxis("Vertical") + Input.GetAxis("Horizontal")));

        rb.velocity = new Vector2(Mathf.Lerp(0, Input.GetAxis("Horizontal")* speed, 1f), Mathf.Lerp(0, Input.GetAxis("Vertical")* speed, 1f));
    }
}

#version 330

layout (location = 0) out vec4 color;

in vec2 v_tex;
in vec3 v_color;
in flat float v_type;

uniform sampler2D atlas;

void main()
{
	color = texture2D(atlas, v_tex);
	if(v_type == 1) {
		if(color.a == 1) {
			color = vec4(v_color, 1.0);
		}
	}
}

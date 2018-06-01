#version 330

layout (location = 0) in vec2 in_pos;
layout (location = 1) in vec2 in_tex;
// Only used if in_type == 1
layout (location = 2) in vec3 in_color;
// 0 = ui element, 1 = text
layout (location = 3) in float in_type;

uniform mat4 projection;
uniform mat4 view;

out vec2 v_tex;
out vec3 v_color;
out float v_type;

void main()
{
	gl_Position = projection * view * vec4(in_pos, 0.0, 1.0);
	v_tex = in_tex;
	v_color = in_color;
	v_type = in_type;
}